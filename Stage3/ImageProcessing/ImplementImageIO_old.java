import java.io.*;
import java.awt.*;
import java.awt.image.*;
import imagereader.IImageIO;
import javax.imageio.*;

public class ImplementImageIO implements IImageIO
{
    private static final int BYTE_HEAD=14;//bmp文件头0-13位
    private static final int BYTE_INFO=40;//bmp信息部分14-53位
    
    private FileInputStream file;

    public Image myRead(String filePath)
    {
        // get input stream
        try{
        this.file = new FileInputStream(filePath);
        }
        catch(IOException e)
        { 
            e.printStackTrace(); 
 	}
        //IO读取异常，FileInputStream和FileOutStream必须使用异常处理
        /*位图头
 23         * 字节 #0-1    保存位图文件的标识符，这两个字节的典型数据是BM。
 24         * 字节 #2-5    使用一个dword保存位图文件大小。
 25         * 字节 #6-9    是保留部分，留做以后的扩展使用,对实际的解码格式没有影响。
 26         * 字节 #10-13    保存位图数据位置的地址偏移，也就是起始地址。
 28         */
        // get bmp header info
        byte header[] = new byte[BYTE_HEAD];
        try{
        file.read(header, 0, BYTE_HEAD);
        }
        catch(IOException e)
        { 
            e.printStackTrace(); 
 	}
        /* 保存位图信息
         * 字节 #14-17    定义以下用来描述影像的区块（BitmapInfoHeader）的大小。
         * 字节 #18-21    保存位图宽度（以像素个数表示）。
         * 字节 #22-25    保存位图高度（以像素个数表示）。
         * 字节 #26-27    保存所用彩色位面的个数。不经常使用。
         * 字节 #28-29    保存每个像素的位数，它是图像的颜色深度。常用值是1、4、8（灰阶）和24（彩色）
         * 字节 #30-33    定义所用的压缩算法。允许的值是0、1、2、3、4、5。
         *                     0 - 没有压缩（也用BI_RGB表示）
         *                     1 - 行程长度编码 8位/像素（也用BI_RLE8表示）
         *                     2 - 行程长度编码4位/像素（也用BI_RLE4表示）
         *                     3 - Bit field（也用BI_BITFIELDS表示）
         *                     4 - JPEG图像（也用BI_JPEG表示）
         *                     5 - PNG图像（也用BI_PNG表示）
         *                    然而，由于大多数位图文件都是不压缩的，所以最常用的值是0。
         * 字节 #34-37    保存图像大小。这是原始（:en:raw）位图数据的大小，不要与文件大小混淆。
         * 字节 #38-41    保存图像水平方向分辨率。
         * 字节 #42-45    保存图像竖值方向分辨率。
         * 字节 #46-49    保存所用颜色数目。
         * 字节 #50-53    保存所用重要颜色数目。当每个颜色都重要时这个值与颜色数目相等。
         * */
        // get bmp data info 
        
        byte info[] = new byte[BYTE_INFO];
        try{
        file.read(info, 0, BYTE_INFO);
        }
	catch(IOException e)
        { 
            e.printStackTrace(); 
 	}
        int width = ((int)info[7]<< 24)  | ((int)info[6]<< 16)  | ((int)info[5]<< 8) | (int)info[4];
  	int height = ((int)info[11]<< 24)  | ((int)info[10]<< 16)  | ((int)info[9]<< 8) | (int)info[8];
  	//bitCount = ((int)info[14]<<8 | (int)info[15]);
	int imageSize = ((int)info[23]<< 24)  | ((int)info[22]<< 16)  | ((int)info[21]<< 8) | (int)info[20];
	
  	// construct image array
  	// if not a multiple of 4, then pad it
  	int padCount = (imageSize / height) - width * 3;
  	if(padCount==4)
  	    padCount=0;
  	int pic[] = new int[imageSize];
  	byte barr[] = new byte[imageSize];
  	try{
  	file.read(barr, 0, imageSize);
  	}
  	catch(IOException e)
        { 
            e.printStackTrace(); 
 	}
  	int index = 0;
  	for(int i=0; i<height; i++)
  	{
  	    for(int j=0; j<width; j++)
  	    {
  	    	pic[width*(height-i-1)+j] = (255<< 24) | ((int)barr[index+2] << 16) | ((int)barr[index+1]<< 8) |(int)barr[index];  
		index += 3;
  	    }
  	    index += padCount;
  	}
  	Image im = null;
  	im = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height,pic, 0, width));
  	try{
        file.close();
        }
        catch(IOException e)
        { 
            e.printStackTrace(); 
 	}
        return im;
    }

    public Image myWrite(Image image,String filepath)
    {
    	try  
        {  
            File file = new File(filepath + ".bmp");
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), 
                                                    image.getHeight(null), 
                                                    BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.drawImage(image, 0, 0, null); 
            graphics.dispose();
            ImageIO.write(bufferedImage, "bmp", file); 
        }  
        catch (Exception e)  
        {  
            e.printStackTrace(System.out);  
        }  
        
        return image;
    }
}
