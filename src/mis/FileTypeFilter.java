package mis;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter {

	private final String extention;
	private final String description;
	
	public FileTypeFilter(String extention, String description) {
	
		this.extention = extention;
		this.description = description;
	}
	@Override
	public boolean accept(File file) {
		if(file.isDirectory())
		{
			return true;
		}
		return file.getName().endsWith(extention);
	}
	@Override
	public String getDescription() {
		return description + String.format(" (*%s)", extention);
	}
}
