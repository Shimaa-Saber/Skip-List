import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class DatabaseTest {
    @Test
    void regionsearch() {
        Database data = new Database();
        RectangleClass rect = new RectangleClass(0, 0, 1000, 10);
        KVPair<String, RectangleClass> pair = new KVPair<>("r3", rect);
        data.insert(pair);
        RectangleClass rect5 = new RectangleClass(20, 12, 3, 3);
        KVPair<String, RectangleClass> pair5 = new KVPair<>("r5", rect5);
        data.insert(pair5);
        RectangleClass rect6 = new RectangleClass(23, 15, 100, 100);
        KVPair<String, RectangleClass> pair6 = new KVPair<>("r7", rect6);
        data.insert(pair6);
        RectangleClass rect7 = new RectangleClass(23, 12, 3, 3);
        KVPair<String, RectangleClass> pair7 = new KVPair<>("r8", rect7);
        data.insert(pair7);
        RectangleClass rect8 = new RectangleClass(20, 15, 3, 3);
        KVPair<String, RectangleClass> pair8 = new KVPair<>("r9", rect8);
        data.insert(pair8);

        assertTrue(data.regionsearch(900, 5, 1, 1));
        assertTrue(data.regionsearch(22, 14, 2, 2));
        assertFalse(data.regionsearch(900, 5, 0, 0));
    }

    @Test
    void intersections() {
        Database data1 = new Database();
        RectangleClass rect1 = new RectangleClass(11, 11, 5, 5);
        KVPair<String, RectangleClass> pair1 = new KVPair<>("r1", rect1);
        data1.insert(pair1);

        RectangleClass rect2 = new RectangleClass(10, 10, 15, 15);
        KVPair<String, RectangleClass> pair2 = new KVPair<>("r2", rect2);
        data1.insert(pair2);

        RectangleClass rect3 = new RectangleClass(0, 0, 1000, 10);
        KVPair<String, RectangleClass> pair3 = new KVPair<>("r5", rect3);
        data1.insert(pair3);

        RectangleClass rect4 = new RectangleClass(0, 0, 10, 1000);
        KVPair<String, RectangleClass> pair4 = new KVPair<>("r4", rect4);
        data1.insert(pair4);

        assertTrue(data1.intersections());
    }

    @Test
    void removeValue() {
        //SkipList<String, RectangleClass> obj = new SkipList<>();
        Database obj=new Database();

        RectangleClass R1 = new RectangleClass(20, 10, 5, 5);
        KVPair<String, RectangleClass> rect9 = new KVPair<>("Rectangle1", R1);


        RectangleClass R2 = new RectangleClass(10, 5, 9, 2);
        KVPair<String, RectangleClass> rect10 = new KVPair<>("Rectangle2", R2);


        RectangleClass R3 = new RectangleClass(13, 15, 9, 12);
        KVPair<String, RectangleClass> rect11 = new KVPair<>("Rectangle3", R3);


        obj.insert(rect9);
        obj.insert(rect10);
        obj.insert(rect11);

        boolean removedPair = obj.removeByValue(10,5,9,2);

        assertTrue(removedPair);

        //assertEquals(2, obj.size());
    }
    @Test
    void removeKey() {
        Database obj1 = new Database();

        RectangleClass R4 = new RectangleClass(20, 10, 5, 5);
        KVPair<String, RectangleClass> rect12 = new KVPair<>("Rectangle1", R4);

        RectangleClass R5 = new RectangleClass(10, 5, 9, 2);
        KVPair<String, RectangleClass> rect13 = new KVPair<>("Rectangle2", R5);

        RectangleClass R6 = new RectangleClass(13, 15, 9, 12);
        KVPair<String, RectangleClass> rect14 = new KVPair<String, RectangleClass>("Rectangle3", R6);

        obj1.insert(rect12);
        obj1.insert(rect13);
        obj1.insert(rect14);

        boolean removedPair1 = obj1.remove("Rectangle1");
        assertTrue(removedPair1);
    }
    @Test
    public void searchTest() {
        Database SearchData=new Database();

        RectangleClass Rectangle15 = new RectangleClass(20, 10, 5, 5);
        KVPair<String, RectangleClass> data5 = new KVPair<>("Rectangle15",  Rectangle15);


        RectangleClass  Rectangle16 = new RectangleClass(10, 5, 9, 2);
        KVPair<String, RectangleClass> data6 = new KVPair<>("Rectangle16",  Rectangle16);


        RectangleClass  Rectangle17 = new RectangleClass(13, 15, 9, 12);
        KVPair<String, RectangleClass> data7 = new KVPair<>("Rectangle17", Rectangle17);

        RectangleClass  Rectangle18 = new RectangleClass(13, 15, 0, 0);
        KVPair<String, RectangleClass> data8 = new KVPair<>("Rectangle18", Rectangle18);

        SearchData.insert(data5);
        SearchData.insert(data6);
        SearchData.insert(data7);
        SearchData.insert(data8);

        boolean res = SearchData.search("Rectangle16");
        assertTrue(res);

    }

    @Test
    public void InsertTest() {
      /*SkipList list= new SkipList();

      //ArrayList<KVPair<String,RectangleClass>> res= new ArrayList<KVPair<String,RectangleClass>>();
      RectangleClass R1=new RectangleClass(20,10,5,5);
      KVPair<String,RectangleClass> rect1=new KVPair<String,RectangleClass>("Rectangle1",R1);

      RectangleClass R2=new RectangleClass(10,5,9,2);
      KVPair<String,RectangleClass> rect2=new KVPair<String,RectangleClass>("Rectangle2",R2);

      RectangleClass R3=new RectangleClass(13,15,9,12);
      KVPair<String,RectangleClass> rect3=new KVPair<String,RectangleClass>("Rectangle3",R3);

      RectangleClass R4=new RectangleClass(13,15,0,0);
      KVPair<String,RectangleClass> rect4=new KVPair<String,RectangleClass>("Rectangle4",R4);

      assertTrue(R1.checkWorldBox());
      assertTrue(R2.checkWorldBox());
      assertTrue(R3.checkWorldBox());
      assertFalse(R4.checkWorldBox());

      list.insert(rect1);
        assertEquals(1, list.size());

        list.insert(rect2);
        assertEquals(2, list.size());

        list.insert(rect3);
        assertEquals(3, list.size());*/

        Database InsertData=new Database();

        RectangleClass Rectangle11 = new RectangleClass(20, 10, 5, 5);
        KVPair<String, RectangleClass> data1 = new KVPair<>("Rectangle1",  Rectangle11);


        RectangleClass  Rectangle12 = new RectangleClass(10, 5, 9, 2);
        KVPair<String, RectangleClass> data2 = new KVPair<>("Rectangle2",  Rectangle12);


        RectangleClass  Rectangle13 = new RectangleClass(13, 15, 9, 12);
        KVPair<String, RectangleClass> data3 = new KVPair<>("Rectangle3", Rectangle13);

        RectangleClass  Rectangle14 = new RectangleClass(13, 15, 0, 0);
        KVPair<String, RectangleClass> data4 = new KVPair<>("Rectangle4", Rectangle14);

        boolean Insert1 = InsertData.insert(data1);
        boolean Insert2 = InsertData.insert(data2);
        boolean Insert3 = InsertData.insert(data3);
        boolean Insert4 = InsertData.insert(data4);

        assertTrue(Insert1);
        assertTrue(Insert2);
        assertTrue(Insert3);
        assertFalse(Insert4);


    }
}