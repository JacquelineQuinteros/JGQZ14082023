package jgqz14082023.accesoadatos;

import jgqz14082023.entidadesdenegocio.Tarea;
import java.util.*;
import java.sql.*;

public class TareaDAL {
    static String getFields() {
        return "r.Id, r.Titulo, r.Descripcion";
    }
    
    private static String getSelect(Tarea pTask){
        String sql = "SELECT ";
        if (pTask.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pTask.getTop_aux() + " ";
        }
        sql += (getFields() + " FROM Tareas r");
        return sql;
    }

    private static String addOrderBy(Tarea pTask) {
        String sql = " ORDER BY r.Id DESC";
        if (pTask.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pTask.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int create(Tarea pTask) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO Tareas(Titulo, Descripcion) VALUES(?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pTask.getTitulo());
                ps.setString(2, pTask.getDescripcion());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int update(Tarea pTask) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Tareas SET Titulo=?, Descripcion=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pTask.getTitulo());
                ps.setString(2, pTask.getDescripcion());
                ps.setInt(3, pTask.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int delete(Tarea pTask) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Tareas WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pTask.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }    
    
    static int asignarDatosResultSet(Tarea pTask, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pTask.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pTask.setTitulo(pResultSet.getString(pIndex)); // index 2
        pIndex++;
        pTask.setDescripcion(pResultSet.getString(pIndex)); // index 3
        return pIndex;
    }
    
    private static void getData(PreparedStatement pPS, ArrayList<Tarea> pTask) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) { 
                Tarea task = new Tarea(); 
                asignarDatosResultSet(task, resultSet, 0);
                pTask.add(task);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static Tarea getById(Tarea pTask) throws Exception {
        Tarea task = new Tarea();
        ArrayList<Tarea> tasks = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(pTask);
            sql += " WHERE r.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pTask.getId());
                getData(ps, tasks);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (tasks.size() > 0) {
            task = tasks.get(0);
        }
        return task;
    }

    public static ArrayList<Tarea> getAll() throws Exception {
        ArrayList<Tarea> tasks = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(new Tarea());
            sql += addOrderBy(new Tarea());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                getData(ps, tasks);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return tasks;
    }
    
    static void querySelect(Tarea pDoc, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pDoc.getId() > 0) {
            pUtilQuery.AgregarWhereAnd(" r.Id=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pDoc.getId()); 
            }
        } if (pDoc.getTitulo() != null && pDoc.getTitulo().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Titulo LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pDoc.getTitulo() + "%"); 
            }
        } if (pDoc.getDescripcion() != null && pDoc.getDescripcion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Descripcion LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pDoc.getDescripcion() + "%"); 
            }
        }
    }

    public static ArrayList<Tarea> Search(Tarea pTask) throws Exception {
        ArrayList<Tarea> tasks = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(pTask);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pTask, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += addOrderBy(pTask);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pTask, utilQuery);
                getData(ps, tasks);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return tasks;
    }
}
