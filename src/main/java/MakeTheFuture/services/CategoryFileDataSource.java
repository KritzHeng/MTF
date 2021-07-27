package MakeTheFuture.services;

import MakeTheFuture.models.Category;
import MakeTheFuture.models.DataList;

import java.io.*;

public class CategoryFileDataSource implements DataSource{
    private String fileDirectoryName;
    private String fileName;
    private DataList categorise;

    public CategoryFileDataSource (String fileDirectoryName, String fileName) {
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
            Category category = new Category(data[0].trim(),Integer.parseInt(data[1].trim()),Integer.parseInt(data[2].trim())
                    ,Integer.parseInt(data[3].trim())
                    ,Integer.parseInt(data[4].trim()),Integer.parseInt(data[5].trim()),data[6].trim());//ID,NAME

            categorise.addCategories(category);
        }
        reader.close();
    }


    @Override
    public DataList getData () {
        try {
            categorise = new DataList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return categorise;
    }

    @Override
    public void setData (DataList categorise) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Category category: categorise.toListCategory()) {
                String line =
                        category.getNameCate() + "," + category.getNumCateTotal() + ","
                                + category.getNumCateGeneral() + "," + category.getNumCateForward() + ","
                                + category.getNumCateWeekly()+"," + category.getNumCateProject()+ "," + category.getNameAllCate();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
