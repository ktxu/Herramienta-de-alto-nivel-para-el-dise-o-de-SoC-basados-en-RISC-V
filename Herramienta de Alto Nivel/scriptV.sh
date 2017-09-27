#!/bin/bash
#chmod a+x scriptV.sh #Ejecutar: ./scriptV.sh
#export TOP=$(pwd) #export RISCV=$TOP/riscv #export PATH=$PATH:$RISCV/bin
#export TOP=$(pwd) #export RISCV=$TOP/riscv32i #export PATH+=":/opt/riscv32i/bin"

echo "Compilando con toolchain de RISC-V: Programa.c -> Programa.o" 
riscv32-unknown-elf-gcc Programa.c -o Programa.o -m32 -march=RV32I ##FD ##-msoft-float     
echo "Generando archivo dump: Programa.o -> Programa.dump"
riscv32-unknown-elf-objdump -d Programa.o > Programa.dump   
echo "Generando código en hexadecimal: Programa.o -> Programa.txt" 
elf2hex 4 32768 Programa.o > Programa.txt

