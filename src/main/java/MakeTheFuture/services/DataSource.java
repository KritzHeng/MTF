package MakeTheFuture.services;


import MakeTheFuture.models.DataList;

public interface DataSource {//ดึงข้อมูลนักเรียน
    DataList getData ();

    void setData (DataList dataList); //นำgeneralList มา set
}
