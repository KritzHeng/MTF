package MakeTheFuture.services;



import MakeTheFuture.models.General;
import MakeTheFuture.models.DataList;

import java.io.*;

public class GeneralFileDataSource implements DataSource {

    private String fileDirectoryName;
    private String fileName;
    private DataList generals;

    public GeneralFileDataSource (String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    private void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;//เอาข้อมูลจากcsv
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");//split ค่าแต่ละค่า ขั้นด้วย ","
            General general = new General(data[0].trim()
                    ,data[1].trim(),data[2].trim()
                    ,data[3].trim(),data[4].trim()
                    ,data[5].trim(),data[6].trim());//ID,NAME

            generals.addGeneral(general);
        }
        reader.close();
    }


    @Override
    public DataList getData () {
        try {
            generals = new DataList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return generals;
    }

    @Override
    public void setData (DataList dataList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (General general: dataList.toListGeneral()) {
                String line = general.getCategory()+","+
                        general.getName() + ","
                        + general.getDate() + ","
                        + general.getTimeStart() + ","
                        + general.getTimeEnd()+","
                        + general.getPriority()+","
                        + general.getStatus();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }


}
