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
    public void testDelete() throws Exception {
        System.out.println("delete");
        Tarea pTask = null;
        int expResult = 0;
        int result = TareaDAL.delete(pTask);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asignarDatosResultSet method, of class TareaDAL.
     */
    @Test
    public void testAsignarDatosResultSet() throws Exception {
        System.out.println("asignarDatosResultSet");
        Tarea pTask = null;
        ResultSet pResultSet = null;
        int pIndex = 0;
        int expResult = 0;
        int result = TareaDAL.asignarDatosResultSet(pTask, pResultSet, pIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class TareaDAL.
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        Tarea pTask = null;
        Tarea expResult = null;
        Tarea result = TareaDAL.getById(pTask);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class TareaDAL.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<Tarea> expResult = null;
        ArrayList<Tarea> result = TareaDAL.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of querySelect method, of class TareaDAL.
     */
    @Test
    public void testQuerySelect() throws Exception {
        System.out.println("querySelect");
        Tarea pDoc = null;
        ComunDB.UtilQuery pUtilQuery = null;
        TareaDAL.querySelect(pDoc, pUtilQuery);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Search method, of class TareaDAL.
     */
    @Test
    public void testSearch() throws Exception {
        System.out.println("Search");
        Tarea pTask = null;
        ArrayList<Tarea> expResult = null;
        ArrayList<Tarea> result = TareaDAL.Search(pTask);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
