import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

public class Division{

	public static void main(String[] args){

		ClassWriter cw=new ClassWriter(0);
		cw.visit(V11, ACC_PUBLIC+ACC_SUPER, "ClassDiv", null, "java/lang/Object", null);
	
		//Create the class
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}
		
		//create the main method
		{
			MethodVisitor mv=cw.visitMethod(ACC_PUBLIC+ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
			mv.visitCode();
			
            //Initializing the integers and performing the division
            mv.visitIntInsn(BIPUSH, 120);
            mv.visitVarInsn(ISTORE,1);
			mv.visitIntInsn(BIPUSH, 5);
			mv.visitVarInsn(ISTORE,2);
            mv.visitVarInsn(ILOAD,1);
            mv.visitVarInsn(ILOAD,2);
			mv.visitInsn(IDIV);
			mv.visitVarInsn(ISTORE,3);

            //Printing the integers
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("120 : 5 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(ILOAD,3);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

            //Initializing the longs and performing the division
			mv.visitLdcInsn(324000l);
			mv.visitVarInsn(LSTORE,4);
            mv.visitLdcInsn(2400l);
			mv.visitVarInsn(LSTORE,6);
			mv.visitVarInsn(LLOAD,4);
			mv.visitVarInsn(LLOAD,6);
			mv.visitInsn(LDIV);
			mv.visitVarInsn(LSTORE,8);

            //Printing the longs
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("324000 : 2400 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(LLOAD,8);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
			
			
            //Initializing the doubles and performing the division
			mv.visitLdcInsn(76.22);
			mv.visitVarInsn(DSTORE,10);
            mv.visitLdcInsn(11.43);
			mv.visitVarInsn(DSTORE,12);
			mv.visitVarInsn(DLOAD,10);
			mv.visitVarInsn(DLOAD,12);
			mv.visitInsn(DDIV);
			mv.visitVarInsn(DSTORE,14);

            //Printing the doubles
            mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("76.22 : 11.43 = ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
			mv.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(DLOAD,14);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);

			mv.visitInsn(RETURN); 
			mv.visitMaxs(20, 20);
			mv.visitEnd();
		}

        //Create the bytearray and writing the class file
		
        byte[] b = cw.toByteArray();

        try{
            FileOutputStream out = new FileOutputStream("ClassDiv.class");
            out.write(b);
            out.close();
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Done! File was succesfully created.");

	}//end main

}//end class