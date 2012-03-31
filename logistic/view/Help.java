package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Help {

	public static String readIntroduce(String fileName) {

		File myFile = new File(fileName);
		StringBuffer helpOrAboutString = new StringBuffer("");
		String str;
		BufferedReader in;

		if (!myFile.exists()) {
			System.err.println("Can't Find " + fileName);
		} else {
			try {
				in = new BufferedReader(new FileReader(myFile));
				while ((str = in.readLine()) != null) {
					helpOrAboutString.append(str+"\n");
				}
				return helpOrAboutString.toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(readIntroduce("help.txt"));
	}
}
