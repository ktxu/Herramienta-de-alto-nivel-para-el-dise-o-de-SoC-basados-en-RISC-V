#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <libxml/xmlmemory.h>
#include <libxml/parser.h>
#include <libxml/tree.h>
#include <math.h>
#include <unistd.h>

//gcc scriptgod.c -o scriptgod `xml2-config --cflags --libs` -lm

//-------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------archivos programa
//Las instrucciones vienen de 1 por linea

char StringHexByte0[8], StringHexByte1[8], StringHexByte2[8],StringHexByte3[8];  
char StringHexPrimero[8];
FILE* fProgramaFull;
FILE* fProgramaLite;
FILE* fProgramaLow;
FILE* fProgramaHigh;
int SizeProgram=0;
int lineaSxlineaE=2; //en archivo ProgramaLow vs Programa
int GPIO_counter=0;
char *xmlFile = NULL;
char *codeFile = NULL;
char *programFile = NULL;

void bin2decimal(char NumBin[]); 
int getDirDump(int End);
int eFlag = 0;

int generateProgramFull()
{
	//SystemCalls o scriptV.sh
	char buffer[512];
	sprintf(buffer,"riscv32-unknown-elf-gcc %s -o Programa.o -m32 -march=RV32I",programFile);
	system(buffer);
	system("riscv32-unknown-elf-objdump -d Programa.o > Programa.dump");
	system("elf2hex 4 32768 Programa.o > Programa.txt");
	return 0;
}

int generateProgramLite()
{
	int BeginAddress, EndAddress,contador=0,control=0;
 	BeginAddress=getDirDump(0);
    EndAddress=getDirDump(1);
	SizeProgram = EndAddress - BeginAddress;
	if(SizeProgram <= 0 ) //no tiene sentido el tamaño
	{
		printf("\nError con tamaño del archivo .dump \n");
		return -1; 
	}     

	for (int ex=0;ex<=29;ex++)
	{  
		if( (int)pow(2,ex) > SizeProgram)
		{
			SizeProgram = (int)pow(2,ex);
			ex= 30;
		}
	}

	fProgramaFull = fopen("Programa.txt","r");//Generado por el elf2hex
	fProgramaLite = fopen ("CodigoPrograma.txt", "w+");//codigo filtrado sin ceros iniciales
	SizeProgram = SizeProgram/lineaSxlineaE; 
	char linea[16];
	char comparador[8];
    strcpy(comparador, "00000000");

	while (contador < SizeProgram) //Mientras no se detecte fin de archivo;
	   {
				fgets (linea, sizeof(linea), fProgramaFull); //lee linea por linea
				if(strncmp(linea,comparador,8) != 0) //detecta inicial de instrucciones
				{
					control = 1;
				} 
				
				if(control == 1)
				{
					if(contador < SizeProgram)
					{
						fprintf(fProgramaLite, "%s",linea);  //se copia la instruccion sin ceros iniciales				
					}
					contador++;
				}
		}

	fclose(fProgramaFull);
	fclose(fProgramaLite);
	return 0;
}

int generateSoCCode(char* file)
{
	fProgramaLite = fopen (file, "r");//codigo filtrado sin ceros iniciales
	fProgramaLow = fopen ("CodigoProgramaLOW.txt", "w+");//MSB de la instruccion
    fProgramaHigh = fopen ("CodigoProgramaHIGH.txt", "w+");//LSB de la instruccion
	char StringBinary[16];	
	while (!feof(fProgramaLite) ) //Mientras no se detecte fin de archivo de programa;
	   {
			fgets (StringBinary, sizeof(StringBinary), fProgramaLite); //lee linea por linea
			bin2decimal(StringBinary);
			fprintf(fProgramaLow, "%s \n",StringHexByte0);  // Byte 0 LSB de la instrucción
			fprintf(fProgramaLow, "%s \n",StringHexByte1);  // Byte 1 de la instrucción
			fprintf(fProgramaHigh, "%s \n",StringHexByte2);  // Byte 2 LSB de la instrucción
			fprintf(fProgramaHigh, "%s \n",StringHexByte3);  // Byte 3 de la instrucción				
	   }
	fclose(fProgramaLite);
	fclose(fProgramaLow);
	fclose(fProgramaHigh);
	return 1;
}

int size() {

    FILE * fp1;
    fp1 = fopen ("CodigoProgramaHIGH.txt", "r"); 
    int i=0;
    int dirinicio =32768;
	int programStart = 15;
	int dirend;
	int k = 0;
	int bits=0;
	char line[16];

	while (!feof(fp1) ) //Mientras no se detecte fin de archivo;
	{
		i++;
		fgets(line, sizeof(line), fp1);
    }

	fclose(fp1);		
	for (k=0;k<=32;k++){  
		if( (int)pow(2,k) >= i){bits=k;break;}
	}
	
	dirend = dirinicio + i-4;
	printf("Cantidad de direcciones de memoria ROM: %d \n",i);
	printf("Parámetro ROM_ADDR_BITS de ROM: %d \n",bits);
	printf("Parámetro ROM_ADDR_START_BITS de ROM: %d\n",programStart);
	printf("Parámetro BEGIN_ADDR_ROM_PROGRAM de ROM: 0x%02X\n",dirinicio);
	printf("Parámetro END_ADDR_ROM_PROGRAM de ROM: 0x%02X\n",dirend);	
}

int getDirDump(int End)
{
	FILE * fdump;
	fdump = fopen("Programa.dump","r");
	char line[64];
	int sLine=-1;
	if(End==0)
	{	
		while(sLine<0)
		{
			fgets(line, sizeof(line), fdump);
			if(strchr(line, '>')>0)
			{
				sLine=1;
				fgets(line, sizeof(line), fdump);
			}
		} 
	}
	else
	{
		while(!feof(fdump)){fgets(line, sizeof(line), fdump);}
	}

	char initDir[16];
	initDir[0]='0';
	initDir[1]='x';
	int contador=(int) (strchr(line, ':')-line);
	int index=0;
	int pos=0;
	while(index<contador)
	{
		if(line[index]!=' ')
		{
			initDir[pos+2]=line[index];
			pos=pos+1;
		}
		index=index+1;
	}
	initDir[pos+2] = '\0'; 
	int dir=(int)strtol(initDir, NULL, 0);
	fclose(fdump);
	return dir;
}

void bin2decimal(char NumBin[])
{
	int n;	
	for (n=0;n<=7;n++){   
		if(n==0 || n==1){StringHexByte3[n] = NumBin[n];}    // Byte 3 MSB
		else if (n==2 || n==3){StringHexByte2[n-2] = NumBin[n];}  // Byte 2
		else if (n==4 || n==5){StringHexByte1[n-4] = NumBin[n];}  // Byte 1
		else {StringHexByte0[n-6] = NumBin[n];}  // Byte 0 LSB
	}
}

int generateProgram()
{
	generateProgramFull();
	generateProgramLite();
}

//---------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------xml

void ModuleData(xmlNode * a_node)
{
    xmlNode *cur_node = NULL;
    xmlChar *value;
    for (cur_node = a_node; cur_node; cur_node = cur_node->next) {
        if (cur_node->type == XML_ELEMENT_NODE) {
            value = xmlNodeGetContent(cur_node);
            printf(" %s:%s \n", cur_node->name,value);
            xmlFree(value);
        }
    }
}

void getConnections(xmlNodePtr cur)
{
    cur = cur->xmlChildrenNode;
    while (cur != NULL) 
    {
        if ((!xmlStrcmp(cur->name, (const xmlChar *)"conection"))) 
        {
			printf("%s\n","Conection");
			xmlNode *cur_node = NULL;
   			xmlChar *value;
			xmlNode * a_node=cur->xmlChildrenNode;
    		for (cur_node = a_node; cur_node; cur_node = cur_node->next) 
			{
        		if (cur_node->type == XML_ELEMENT_NODE) 
				{
            		value = xmlNodeGetContent(cur_node);
            		printf(" %s:%s \n", cur_node->name,value);
            		xmlFree(value);
        		}
    		}
        }
        cur = cur->next;
    }
    return;

}

void moduleGPIO(xmlNode* a_node,FILE* socH,FILE* socE,FILE* socM)
{
	int direction=0;
	int size=0;
	int mode=0; //0 input - 1 output 
    xmlNode *cur_node = NULL;
    xmlChar *value;
    for (cur_node = a_node; cur_node; cur_node = cur_node->next) {
        if (cur_node->type == XML_ELEMENT_NODE) {
            value = xmlNodeGetContent(cur_node);
            if( strncmp(cur_node->name,"size",4)==0){size=atoi(value);}
			else if( strncmp(cur_node->name,"mode",4)==0)
				{
				if(strncmp(value,"input",5)==0)
				{
					mode=0;
				}
				else
				{
					mode=1;
				}
			}
			else if( strncmp(cur_node->name,"direction",9)==0){direction=atoi(value);}
            xmlFree(value);
        }
    }

	if(mode==0)  //input
	{
	fprintf(socH,",input [%d:0] DataInput_GPIO%d \n",size-1,GPIO_counter);

	fprintf(socE,"wire [%d:0] DataOutput_GPIO%d;\n",size-1,GPIO_counter);
	fprintf(socE,"GPIO #(.Width(%d))\n",size);
	fprintf(socE,"GPIO%d (.CLK(CLK),.Reset(Reset),.DataInput(DataInput_GPIO%d),.DataOutput(DataOutput_GPIO%d));\n",GPIO_counter,GPIO_counter,GPIO_counter);

	fprintf(socM,"if (AddressIO == 32'd%d) begin if(WriteIO == 1'b0)begin DataInput <= DataOutput_GPIO%d;end end\n",direction,GPIO_counter);
	}
	else   //output
	{
	fprintf(socH,",output [%d:0] DataOutput_GPIO%d \n",size-1,GPIO_counter);

	fprintf(socE,"reg [%d:0] DataInput_GPIO%d=%d'd0;\n",size-1,GPIO_counter,size);
	fprintf(socE,"GPIO #(.Width(%d))\n",size);
	fprintf(socE,"GPIO%d (.CLK(CLK),.Reset(Reset),.DataInput(DataInput_GPIO%d),.DataOutput(DataOutput_GPIO%d));\n",GPIO_counter,GPIO_counter,GPIO_counter);

	fprintf(socM,"if (AddressIO == 32'd%d) begin if(WriteIO == 1'b1)begin DataInput_GPIO%d <= DataOutput;end end\n",direction,GPIO_counter);
	}
	fprintf(socE,"\n");
	GPIO_counter=GPIO_counter+1;
	
}

void moduleCPU(xmlNode* a_node,FILE* socH,FILE* socE,FILE* socM)
{ 
    xmlNode *cur_node = NULL;
    xmlChar *value;
    for (cur_node = a_node; cur_node; cur_node = cur_node->next) {
        if (cur_node->type == XML_ELEMENT_NODE) {
            value = xmlNodeGetContent(cur_node);
            /*if( strncmp(cur_node->name,"size",4)==0){size=atoi(value);}
			else if( strncmp(cur_node->name,"mode",4)==0)
				{
				if(strncmp(value,"input",5)==0)
				{
					mode=0;
				}
				else
				{
					mode=1;
				}
			}
			else if( strncmp(cur_node->name,"direction",9)==0){direction=atoi(value);}*/
            xmlFree(value);
        }
    }

	fprintf(socE,"reg [31:0] DataInput;\n");
	fprintf(socE,"wire [8:0] AddressIO;\n");
	fprintf(socE,"wire [31:0] DataOutput;\n");
	fprintf(socE,"wire WriteIO;\n");
	fprintf(socE,"CPU #(.WidthData(32),.WidhtAddress(32),.WidthInstruction(32),.StackPointer(32'h8000),.ROM_ADDR_BITS(12),.ROM_WIDTH(8),.BEGIN_ADDR_ROM_PROGRAM(32'h8000),.END_ADDR_ROM_PROGRAM(32'h8FFF),.ProgramStartAddressPC(32'h80A4),.ROM_ADDR_START_BITS(15),.RAM_ADDR_BITS(16),.RAM_WIDTH(8),.RAM_ADDR_START_BITS(10),.InicializarRAM(0),.DIRInicioInicializarRAM(32'h400),.DIRFinInicializarRAM(32'h2D98),.ExtensionI(2),.ExtensionF(0))\n");
	fprintf(socE,"CPU0 (.CLK(CPU_CLK),.Reset(Reset),.DataInputTowardMicro(DataInput),.AddressOutIO(AddressIO),.DataOutputTowardIO(DataOutput),.WritePPI(WriteIO) );\n");
	fprintf(socE,"\n");

}

void getModules (xmlNodePtr cur) 
{
    xmlChar *type;
    cur = cur->xmlChildrenNode;
	FILE* socH=NULL;
	FILE* socE=NULL;
	FILE* socM=NULL;	
	socH = fopen ("socHeaders.txt", "w+");
	socE = fopen ("socElements.txt", "w+");
	socM = fopen ("socMap.txt", "w+");
    while (cur != NULL) 
    {
        if ((!xmlStrcmp(cur->name, (const xmlChar *)"module"))) 
        {
            type = xmlGetProp(cur, "type");
            //printf("Module type: %s\n", type);
			//ModuleData(cur->xmlChildrenNode);
			if(strncmp(type,"gpio",4)==0)
			{
				moduleGPIO(cur->xmlChildrenNode,socH,socE,socM);
			}
			else if(strncmp(type,"cpu",3)==0)
			{
				moduleCPU(cur->xmlChildrenNode,socH,socE,socM);
			}
            xmlFree(type);
            
        }

        cur = cur->next;
    }

	fprintf(socH,"\n");
	fprintf(socM,"\n");
	fclose(socH);
	fclose(socE);
	fclose(socM);
	
	
	
    return;
}

int xmlParser(char* file)
{
	xmlDoc *doc = NULL;
	xmlNode *root_element = NULL;

	LIBXML_TEST_VERSION

    doc = xmlReadFile(file, NULL, 0);

    if (doc == NULL) {printf("Error: No XML file inserted %s\n", file);return(1);}
    root_element = xmlDocGetRootElement(doc);
    getModules(root_element);
	//root_element = xmlDocGetRootElement(doc);
    //getConnections(root_element);
    xmlFreeDoc(doc);
    xmlCleanupParser();
	return 0;

}

void generateSoC()
{
	FILE* socH=NULL;
	FILE* socE=NULL;
	FILE* socM=NULL;
	FILE* top=NULL;	
	socH = fopen ("socHeaders.txt", "r");
	socE = fopen ("socElements.txt", "r");
	socM = fopen ("socMap.txt", "r");
	top = fopen ("Top.v", "w+");

	fprintf(top,"module Top (\n");
	fprintf(top,"input CLK\n");
	fprintf(top,",input Reset\n");
	
	char buffer[512];	
	while (!feof(socH) ) //Headers;
	{
			fgets(buffer, 512, socH);
			fprintf(top,"%s",buffer);
	}

	fprintf(top,");\n");
	fprintf(top,"\n");
	fprintf(top,"reg [30:0] CLKKDIV;\n");
	fprintf(top,"wire CPU_CLK;\n");
	fprintf(top,"\n");

	while (!feof(socE) ) //Elements;
	{
			fgets(buffer, 512, socE);
			fprintf(top,"%s",buffer);
	}

	fprintf(top,"always@(posedge CLK)\n");
	fprintf(top,"begin\n");
	fprintf(top,"CLKKDIV <= CLKKDIV + 1'b1;\n");
	fprintf(top,"\n");

	while (!feof(socM) ) //Elements;
	{
			fgets(buffer, 512, socM);
			fprintf(top,"%s",buffer);
	}

	fprintf(top,"end\n");
	fprintf(top,"\n");
	fprintf(top,"assign CPU_CLK = CLKKDIV[17];\n");
	fprintf(top,"\n");
	fprintf(top,"endmodule\n");

	fclose(socH);
	fclose(socE);
	fclose(socM);
	fclose(top);


}

void clearFiles()
{
system("rm socElements.txt");
system("rm socMap.txt");
system("rm socHeaders.txt");

}


void socCreator()
{
	xmlParser(xmlFile);
	generateSoC();
	clearFiles();
}


void argumentParser(int argc, char **argv)
{

int hFlag = 0;
int codeFlag = 0;
int programFlag = 0;
int xmlFlag = 0;
int opt=0;

while ((opt = getopt(argc, argv, "x:c:p:h::")) != -1) 
{
    switch(opt) 
	{
		case 'x':
			xmlFlag=1;
			xmlFile = optarg;
			break;
		case 'c':
			codeFlag=1;
			codeFile = optarg;
			break;
		case 'p':
			programFlag=1;
			programFile = optarg;
			break;
		case 'h':
			hFlag=1;
			break;
		case '?':
			eFlag=1;
		if (optopt == 'c') {eFlag=1;} 
		else if (optopt == 'x'){eFlag=1;}
		else if (optopt == 'p'){eFlag=1;}
		else if (optopt == 'h'){eFlag=1;}
		break;
	}
}

if(xmlFlag==0)
{
	eFlag=1;
}
if(hFlag==1 || eFlag==1)
{

	if(hFlag==1)
	{
	printf("HELP\n");
	}
	else if(eFlag==1)
	{
	printf("Error en los argumentos.\n");
	}

}
else
{

	if(codeFlag==1 && programFlag==1)
	{
	printf("Se utiliza el programa y se desprecia el compilado. \n");
	}
	if(xmlFlag==1 && programFlag==1)
	{
	printf("generateFull.\n");
	generateProgram();
	generateSoCCode("CodigoPrograma.txt");
	//size();
	socCreator();
	}
	else if(xmlFlag==1 && codeFlag==1)
	{
	printf("generateLite.\n");
	generateSoCCode(codeFile);
	//size();
	socCreator();
	}
	else
	{
	printf("soloXML.\n");
	}

}


}


int main(int argc, char **argv)
{
	argumentParser(argc,argv);	
    return 0;
}
