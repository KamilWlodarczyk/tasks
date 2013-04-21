package file_reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;


public class File_reader extends SwingWorker<List<String>, String> {

    private String filePath = null;
    private JTextArea textArea = null;

    public File_reader(String file, JTextArea textArea) {
        filePath = file;
        this.textArea = textArea;
    }

    @Override
    public List<String> doInBackground() throws Exception {

        List<String> text = new ArrayList<String>();
        BufferedReader bufferReader = null;
        try {

            FileReader reader = new FileReader(filePath);
            bufferReader = new BufferedReader(reader);
            String line = null;

            while ((line = bufferReader.readLine()) != null) {

                text.add(line);
                publish(line);
                Thread.sleep(10);
            }
        } finally {
            try {
                bufferReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text;

    }

    @Override
    protected void done() {
        try {
            System.out.println("Done.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void process(List<String> chunks) {

        for (String tmp : chunks) {
            textArea.append(tmp);
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String file) {
        this.filePath = file;
    }
}