package guet.edu.cn.zhegui.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
public class ImageUtil {
	
	public static final int TEMPLATE_THREE=3;           //3*3模板中值滤波
	public static final int TEMPLATE_FIVE=5;           //5*5模板中值滤波
	//获取图像，根据图像的绝对地址获取图像
	/**
	 * 根据图像的URL返回BufferedImage图像引用
	 * @param imageURL
	 * @return
	 */
	public static BufferedImage getImage(String imageURL){
		FileInputStream fis=null;
		BufferedImage bufferedImage=null;;
		try {
			File file = new File("E:/QRcodeTest/二维码3.jpg");
			fis = new FileInputStream(file);
			bufferedImage = ImageIO.read(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bufferedImage;
	};
	
	//将该像素点转为RGB值，灰度图像
	/**
	 * 映射为RGB的模型，灰度图像映射
	 * @param alpha
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	private static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;
		return newPixel;
	}
	
	
	/**
	 * 图像灰度化处理
	 * @param image
	 * @return
	 */
	public static BufferedImage grayImage(BufferedImage bufferedImage){
		//BufferedImage bufferedImage = ImageIO.read(new File("E:/QRcodeTest/二维码3.jpg"));
		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
/*		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);*/

		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				final int color = bufferedImage.getRGB(i, j);
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;
				int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
				if(i==0 && j==10)
					System.out.println(gray);
				//System.out.println(i + " : " + j + " " + gray);
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayImage.setRGB(i, j, newPixel);
			}
		}
/*		File newFile = new File("E:/QRcodeTest/ok22.jpg");
		ImageIO.write(grayImage, "jpg", newFile);*/
		return grayImage;
	}
	
	/**
	 * 保存图像
	 * @param bufferedImage
	 * @return
	 */
	public static int saveImage(BufferedImage bufferedImage,String filePath,String formatName){
		
		FileOutputStream newfile=null;
		try {
			File saveFile = new File(filePath);
			if(!saveFile.exists()){
				newfile = new FileOutputStream(saveFile);
			}else{
				//JOptionPane.showMessageDialog(null, "文件已存在", "该文件已存在", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
			ImageIO.write(bufferedImage, "jpg", newfile);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
		}
		return 0;
	}
	
	
	/**
	 * 根据阈值对图像进行二值化
	 * @param bufferedImage
	 * @param threshold
	 * @return
	 */
	public static BufferedImage BinarizationIamge(BufferedImage bufferedImage,int threshold){
		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				final int color = bufferedImage.getRGB(i, j);
				int gray = (color >> 16) & 0xff;
				if(gray<threshold){
					gray=0;
				}else{
					gray=255;
				}
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayImage.setRGB(i, j, newPixel);
			}
		}
		return grayImage;
	}
	
	/**
	 * Ostu法，返回阈值
	 * @param image
	 * @return
	 */
	public static int otsu(BufferedImage image) {  
		
		int width = image.getWidth();
		int height = image.getHeight();
		int pixelSum = width * height, threshold = 0;
		int[] pixelCount = new int[256];
		float[] pixelPro = new float[256];

		int[] data = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
				null, 0, image.getWidth());

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				int c = data[w + h * width];
				data[w + h * width] = (int) (c >> 16) & 0xFF;
			}
		}

		// 初始化
		for (int i = 0; i < 256; i++) {
			pixelCount[i] = 0;
			pixelPro[i] = 0;
		}

		// 统计灰度级中每个像素在整幅图像中的个数
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				pixelCount[data[w + h * width]]++;
			}
		}

		// 计算每个像素在整幅图像中的比例
		for (int i = 0; i < 256; i++) {
			pixelPro[i] = (float) (pixelCount[i]) / (float) (pixelSum);
		}

		// 经典ostu算法,得到前景和背景的分割
		// 遍历灰度级[0,255],计算出方差最大的灰度值,为最佳阈值
		float w0, w1, u0tmp, u1tmp, u0, u1, u, deltaTmp, deltaMax = 0;
		for (int i = 0; i < 256; i++) {
			w0 = w1 = u0tmp = u1tmp = u0 = u1 = u = deltaTmp = 0;

			for (int j = 0; j < 256; j++) {
				if (j <= i) // 背景部分
				{
					// 以i为阈值分类，第一类总的概率
					w0 += pixelPro[j];
					u0tmp += j * pixelPro[j];
				} else // 前景部分
				{
					// 以i为阈值分类，第二类总的概率
					w1 += pixelPro[j];
					u1tmp += j * pixelPro[j];
				}
			}

			u0 = u0tmp / w0; // 第一类的平均灰度
			u1 = u1tmp / w1; // 第二类的平均灰度
			u = u0tmp + u1tmp; // 整幅图像的平均灰度
			// 计算类间方差
			deltaTmp = w0 * (u0 - u) * (u0 - u) + w1 * (u1 - u) * (u1 - u);
			// 找出最大类间方差以及对应的阈值
			if (deltaTmp > deltaMax) {
				deltaMax = deltaTmp;
				threshold = i;
			}
		}
		// 返回最佳阈值;
		return threshold; 
	 } 
	
	
	/**
	 * 中值滤波
	 * @param bufferedImage
	 * @param filterTemplate
	 * @return
	 */
	public static BufferedImage medianFiltering(BufferedImage bufferedImage,int filterTemplate){ 
		//System.out.println("进入中值滤波。。。。");
		BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(),
				bufferedImage.getHeight(), bufferedImage.getType());
		int height = bufferedImage.getHeight();
		int width = bufferedImage.getWidth();
		int[][] data=new int[width][height];
		int[][] tempData = new int[width][height];
		
		
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				final int color = bufferedImage.getRGB(i, j);
				int gray = (color >> 16) & 0xff;
				data[i][j]=gray;
			}
		}
		
		if(filterTemplate==ImageUtil.TEMPLATE_THREE){
			//3*3窗口
			System.out.println("wiew222225");
			int[] tempArray= new int[9];
			int count=0;
			for(int x=0;x<width;x++){
				for(int y=0;y<height;y++){
					if(x!=0 && x!=width-1 && y!=0 && y!=height-1){
						tempArray[0] = data[x-1][y-1];  
						tempArray[1] = data[x-1][y];
						tempArray[2] = data[x-1][y+1];
						tempArray[3] = data[x][y-1];  
						tempArray[4] = data[x][y];
						tempArray[5] = data[x][y+1];  
						tempArray[6] = data[x+1][y-1];
						tempArray[7] = data[x+1][y]; 
						tempArray[8] = data[x+1][y+1]; 
						Arrays.sort(tempArray);  
						//minResult = tempArray[4];
						tempData[x][y]=tempArray[4];
					}else{
						tempData[x][y]=data[x][y];
					}
				}
			}
		}else if(filterTemplate==ImageUtil.TEMPLATE_FIVE){
			//5*5窗口
			System.out.println("wie5555");
			int[] tempArray= new int[25];
			for(int x=0;x<width;x++){
				for(int y=0;y<height;y++){
					if(x>=2 && x<=width-3 && y>=2 && y<=height-3){
						int count=0;
						for(int a=x-2;a<=x+2;a++){
							for(int b=y-2;b<=y+2;b++){
								tempArray[count++]=data[a][b];
							}
						}
						Arrays.sort(tempArray);  
						tempData[x][y]=tempArray[12];
					}else{
						tempData[x][y]=data[x][y];
					}
				}
			}
		}
		
		//将图像转换
		for(int w=0;w<width;w++){
			for(int h=0;h<height;h++){
				int gray = tempData[w][h];
				//System.out.println(w+","+h+"处的值==="+gray);
				int newPixel = colorToRGB(255, gray, gray, gray);
				grayImage.setRGB(w, h, newPixel);
			}
		}
		
		return grayImage;
	}
	
	/**
	 * 将data数组创建BufferedImage
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage toImage(int[] data, int width, int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		int[] d = new int[width * height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
                d[j+i*width] = (255<<24)
                			    |(data[j+i*width]<<16)
                			    |(data[j+i*width]<<8)
                			    |(data[j+i*width]);
				//d[j+i*width] = data[j+i*width];
			}
		}
		image.setRGB(0, 0, width, height, d, 0, width);
		return image;
	}
	
//	//返回灰度化得图像后的date
//	public static int[][] getGrayIamge(Image image){
//		return null;
//	}
//	
//	//使用OSTU法将图像二值化
//	public static Image ostuBinarizationIamge(Image imag){
//		return null;
//	} 
//	
//	//使用中值滤波讲图像进行滤波降噪
//	public static Image grayImage(int data){
//		return null;
//	}
}
