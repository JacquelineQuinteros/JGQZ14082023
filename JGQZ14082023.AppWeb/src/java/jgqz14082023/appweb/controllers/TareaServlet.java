package jgqz14082023.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import jgqz14082023.accesoadatos.TareaDAL;
import jgqz14082023.entidadesdenegocio.Tarea;
import jgqz14082023.appweb.utils.*;

@WebServlet(name = "TareaServlet", urlPatterns = {"/Tarea"})
public class TareaServlet extends HttpServlet {
private Tarea obtenerTarea(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Tarea task = new Tarea();
        if (accion.equals("create") == false) {
            task.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        task.setTitulo(Utilidad.getParameter(request, "titulo", ""));
        task.setDescripcion(Utilidad.getParameter(request, "descripcion", ""));
        if (accion.equals("index")) {
            task.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            task.setTop_aux(task.getTop_aux() == 0 ? Integer.MAX_VALUE : task.getTop_aux());
        }
        return task;
    }
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Tarea task = new Tarea();
            task.setTop_aux(10);
            ArrayList<Tarea> tasks = TareaDAL.Search(task);
            request.setAttribute("tareas", tasks);
            request.setAttribute("top_aux", task.getTop_aux());         
            request.getRequestDispatcher("Views/Tarea/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Tarea task = obtenerTarea(request);
            ArrayList<Tarea> tasks = TareaDAL.Search(task);
            request.setAttribute("tareas", tasks);
            request.setAttribute("top_aux", task.getTop_aux());
            request.getRequestDispatcher("Views/Tarea/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Tarea/create.jsp").forward(request, response);
    }
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Tarea task = obtenerTarea(request);
            int result = TareaDAL.create(task);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro registrar una nueva tarea", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    private void requestGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Tarea task = obtenerTarea(request);
            Tarea task_result = TareaDAL.getById(task);
            if (task_result.getId() > 0) {
                request.setAttribute("tarea", task_result);
            } else {
                Utilidad.enviarError("El Id: " + task.getId() + " no existe en la tabla de Tareas", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Tarea/edit.jsp").forward(request, response);
    }
    
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Tarea task = obtenerTarea(request);
            int result = TareaDAL.update(task);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Tarea/details.jsp").forward(request, response);
    }

    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Tarea/delete.jsp").forward(request, response);
    }
    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Tarea task = obtenerTarea(request);
            int result = TareaDAL.delete(task);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");
        switch (accion) {
            case "index":
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
                break;
            case "create":
                request.setAttribute("accion", accion);
                doGetRequestCreate(request, response);
                break;
            case "edit":
                request.setAttribute("accion", accion);
                doGetRequestEdit(request, response);
                break;
            case "delete":
                request.setAttribute("accion", accion);
                doGetRequestDelete(request, response);
                break;
            case "details":
                request.setAttribute("accion", accion);
                doGetRequestDetails(request, response);
                break;
            default:
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");
        switch (accion) {
            case "index":
                request.setAttribute("accion", accion);
                doPostRequestIndex(request, response);
                break;
            case "create":
                request.setAttribute("accion", accion);
                doPostRequestCreate(request, response);
                break;
            case "edit":
                request.setAttribute("accion", accion);
                doPostRequestEdit(request, response);
                break;
            case "delete":
                request.setAttribute("accion", accion);
                doPostRequestDelete(request, response);
                break;
            default:
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response); 
        }
    }
}
