package jgqz14082023.accesoadatos;

import java.sql.ResultSet;
import java.util.ArrayList;
import jgqz14082023.entidadesdenegocio.Tarea;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TareaDALIT {
    
    private static Tarea taskActual;
    
    public TareaDALIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class TareaDAL.
     */
    @Test
    public void test1Crear() throws Exception {
        System.out.println("create");
        Tarea pTask = new Tarea(0, "TEST UNIT", "Test unit");
        int expResult = 0;
        int result = TareaDAL.create(pTask);
        assertNotEquals(expResult, result);
    }

    public int testIndividualQuerySelect(Tarea pTask) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("",null, 0);
        TareaDAL.querySelect(pTask, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }
        
    /**
     * Test of update method, of class TareaDAL.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Tarea pTask = new Tarea();
        pTask.setId(taskActual.getId());
        pTask.setTitulo("TEST UNIT M");
        pTask.setDescripcion("TEST UNIT M");
        int expResult = 0;
        int result = TareaDAL.update(pTask);
        assertNotEquals(expResult, result);
        Tarea taskUpdate = TareaDAL.getById(taskActual);
        assertTrue(taskUpdate.getTitulo().equals(pTask.getTitulo()));
    }

    /**
     * Test of delete method, of class TareaDAL.
     */
    @Test
    public void test7Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = TareaDAL.delete(taskActual);
        assertNotEquals(expResult, result);
        Tarea taskDelete = TareaDAL.getById(taskActual);
        assertTrue(taskDelete.getId() == 0);
    }

    /**
     * Test of getById method, of class TareaDAL.
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        Tarea result = TareaDAL.getById(taskActual);
        assertEquals(taskActual.getId(), result.getId());
    }

    /**
     * Test of getAll method, of class TareaDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<Tarea> result = TareaDAL.getAll();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of querySelect method, of class TareaDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        Tarea pTask = new Tarea();
        pTask.setId(1);
        assertTrue(testIndividualQuerySelect(pTask) == 1);
        pTask.setTitulo("TEST");
        assertTrue(testIndividualQuerySelect(pTask) == 2);
        pTask.setDescripcion("TEST");
        assertTrue(testIndividualQuerySelect(pTask) == 3);
    }

    /**
     * Test of Search method, of class TareaDAL.
     */
    @Test
    public void testSearch() throws Exception {
        System.out.println("Search");
        Tarea pTask = new Tarea(0, "Test","Test");
        ArrayList<Tarea> result = TareaDAL.Search(pTask);
        assertTrue(result.size() > 0);
        taskActual = result.get(0);
    }
    
}
