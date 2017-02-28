package zxw;

/**
 * 160818 
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileCopyWithProgress {

	
	public static void main(String[] args) throws IOException {
		String localSrc = "/root/software/hadoop-2.6.4.tar.gz";
	    String dst = "hdfs://localhost/user/root/hadoop-2.6.4.tar_" + System.currentTimeMillis() + ".tar.gz";
	    
	    InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
	    
	    Configuration conf = new Configuration();
	    FileSystem fs = FileSystem.get(URI.create(dst), conf);
	    OutputStream out = fs.create(new Path(dst), new Progressable(){
			@Override
			public void progress() {
				System.out.print("'");
			}
	    });
	    
	    IOUtils.copyBytes(in, out, conf);
	}

}
