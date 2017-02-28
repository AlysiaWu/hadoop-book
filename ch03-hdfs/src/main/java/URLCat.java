// cc URLCat Displays files from a Hadoop filesystem on standard output using a URLStreamHandler
import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

// vv URLCat
public class URLCat {

  static {
    URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
  }
  
  public static void main(String[] args) throws Exception {
    InputStream in = null;
    try {
      in = new URL("hdfs://localhost/user/root/a.txt").openStream();
      IOUtils.copyBytes(in, System.out, 4096, false);
    } finally {
      IOUtils.closeStream(in);
    }
  }
}
// ^^ URLCat
