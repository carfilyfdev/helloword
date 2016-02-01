import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class TestSamba {

	public static void main(String[] args) {
		/*
		 * try { SmbFile smbFile = new
		 * SmbFile("smb://192.168.2.203/aep_resource/"); smbFile.listFiles();
		 * int length = smbFile.getContentLength();// 得到文件的大小 byte buffer[] =
		 * new byte[length]; SmbFileInputStream in = new
		 * SmbFileInputStream(smbFile); // 建立smb文件输入流 while ((in.read(buffer))
		 * != -1) {
		 * 
		 * System.out.write(buffer); System.out.println(buffer.length); }
		 * in.close(); } catch (Exception e) { e.printStackTrace(); }
		 */

		/*
		 * String p =
		 * "smb://192.168.2.203/aep_resource/deployments/rs.war/farm/0e8cf23003b5466095f9602530179ba9.jpg"
		 * ; String localPath = "C:/Temp"; File file = readFromSmb(p,
		 * localPath);
		 */

		smbPut("smb://192.168.2.203/aep_resource/deployments/rs.war/farm/",
				"C:/Temp/0e8cf23003b5466095f9602530179ba9.jpg");
	}

	public static File readFromSmb(String smbMachine, String localpath) {
		File localfile = null;
		InputStream bis = null;
		OutputStream bos = null;
		try {
			SmbFile rmifile = new SmbFile(smbMachine);
			String filename = rmifile.getName();
			bis = new BufferedInputStream(new SmbFileInputStream(rmifile));
			localfile = new File(localpath + File.separator + filename);
			System.out.println("localfile==" + localfile);
			bos = new BufferedOutputStream(new FileOutputStream(localfile));
			int length = rmifile.getContentLength();
			System.out.println("length==" + length);
			byte[] buffer = new byte[length];
			Date date = new Date();
			bis.read(buffer);
			bos.write(buffer);

			Date end = new Date();
			int time = (int) ((end.getTime() - date.getTime()) / 1000);
			if (time > 0)
				System.out.println("用时:" + time + "秒 " + "速度:" + length / time
						/ 1024 + "kb/秒");
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return localfile;
	}

	public static void smbGet(String remoteUrl, String localDir) {
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile smbFile = new SmbFile(remoteUrl);
			String fileName = smbFile.getName();
			File localFile = new File(localDir + File.separator + fileName);
			in = new BufferedInputStream(new SmbFileInputStream(smbFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			byte[] buffer = new byte[1024];
			while ((in.read(buffer)) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void smbPut(String remoteUrl, String localFilePath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			File localFile = new File(localFilePath);
			String fileName = localFile.getName();
			SmbFile remoteFile = new SmbFile(remoteUrl + File.separator
					+ fileName);
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1024];
			while ((in.read(buffer)) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
