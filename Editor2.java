// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Color;

public class Editor2 {
   public Editor2() {
   }

   public static void main(String[] var0) {
      String var1 = var0[0];
      int var2 = Integer.parseInt(var0[1]);
      int var3 = Integer.parseInt(var0[2]);
      Color[][] var4 = Runigram.read(var1);
      Color[][] var5 = Runigram.scaled(var4, var2, var3);
      Runigram.setCanvas(var4);
      Runigram.display(var4);
      StdDraw.pause(3000);
      Runigram.setCanvas(var5);
      Runigram.display(var5);
   }
}