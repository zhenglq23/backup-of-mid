import java.io.FileInputStream;

public class ImplementImageIO
{
    private static final int BYTE_HEAD=14;//bmp文件头0-13位
    private static final int BTYE_INFO=40;//bmp信息部分14-53位

    public Image myRead(String filePath)
    {
        // get input stream
        FileInputStream file = new FileInputStream(filePath);
        // get bmp header info
        for (int i=0; i<BYTE_HEAD; i++)
        {
        
        }
        // get bmp data info 
        int n=0; // if n=-1, it means to the end
        while((n=file.read())!=-1)
        {
            
        }
    }

}
