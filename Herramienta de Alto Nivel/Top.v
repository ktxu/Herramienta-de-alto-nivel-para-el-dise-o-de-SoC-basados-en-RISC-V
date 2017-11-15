module Top (
input CLK
,input Reset
,output [30:0] DataOutput_GPIO0 
,output [30:0] DataOutput_GPIO1 


);

reg [30:0] CLKKDIV;
wire CPU_CLK;

reg [31:0] DataInput;
wire [8:0] AddressIO;
wire [31:0] DataOutput;
wire WriteIO;
CPU #(.WidthData(32),.WidhtAddress(32),.WidthInstruction(32),.StackPointer(32'h8000),.ROM_ADDR_BITS(12),.ROM_WIDTH(8),.BEGIN_ADDR_ROM_PROGRAM(32'h8000),.END_ADDR_ROM_PROGRAM(32'h8FFF),.ProgramStartAddressPC(32'h80A4),.ROM_ADDR_START_BITS(15),.RAM_ADDR_BITS(16),.RAM_WIDTH(8),.RAM_ADDR_START_BITS(10),.InicializarRAM(0),.DIRInicioInicializarRAM(32'h400),.DIRFinInicializarRAM(32'h2D98),.ExtensionI(2),.ExtensionF(0))
CPU0 (.CLK(CPU_CLK),.Reset(Reset),.DataInputTowardMicro(DataInput),.AddressOutIO(AddressIO),.DataOutputTowardIO(DataOutput),.WritePPI(WriteIO) );

reg [30:0] DataInput_GPIO0=31'd0;
GPIO #(.Width(31))
GPIO0 (.CLK(CLK),.Reset(Reset),.DataInput(DataInput_GPIO0),.DataOutput(DataOutput_GPIO0));

reg [30:0] DataInput_GPIO1=31'd0;
GPIO #(.Width(31))
GPIO1 (.CLK(CLK),.Reset(Reset),.DataInput(DataInput_GPIO1),.DataOutput(DataOutput_GPIO1));


always@(posedge CLK)
begin
CLKKDIV <= CLKKDIV + 1'b1;

if (AddressIO == 32'd40) begin if(WriteIO == 1'b1)begin DataInput_GPIO0 <= DataOutput;end end
if (AddressIO == 32'd41) begin if(WriteIO == 1'b1)begin DataInput_GPIO1 <= DataOutput;end end


end

assign CPU_CLK = CLKKDIV[17];

endmodule
