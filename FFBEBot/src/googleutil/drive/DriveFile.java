package googleutil.drive;
/**
 * Contains most of the data used by the file managed to upload and download files<p>
 * A basic representation of the information a Google drive file has
 * @author Allen
 *
 */
public class DriveFile {
	private String name;
	private String id;
	private String filePath;
	public DriveFile(String name,String id){
		this.name=name;
		this.id=id;
		setFilePath(name);
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
