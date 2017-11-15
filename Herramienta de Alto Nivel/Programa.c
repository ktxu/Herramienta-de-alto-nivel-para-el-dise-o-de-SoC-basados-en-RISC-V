
void dormir();

void main(void) { 
	int* LEDS= 70;
	int* SW= 80;
	*LEDS=0;
	int* UART_RX=101;
	int* UART_TX=102;
	int* UART_RX_RESET=103;
	//int* TIMER=90;
	//int* PERIODO=91;
	int dato=0;

	//*TIMER=8;
	while(1){	
		
	dato=*UART_RX;//*TIMER;
	*LEDS=dato;
	*UART_RX_RESET=0;
	/*if(dato==0){
		*PERIODO=25000000;
		*TIMER=16;}*/
	//else{*UART_TX=50+*SW;}
	if(dato>40){
		int tx=0;
		tx=*SW;
		tx=tx+dato;
		*UART_TX=tx;}
	dormir();
	}
	 

  }


void dormir(){
	int counter=0;
	while(counter<1000000){counter=counter+1;}	
	}
