<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jgqz14082023.entidadesdenegocio.Tarea"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Tarea> tareas = (ArrayList<Tarea>) request.getAttribute("tareas");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (tareas == null) {
        tareas = new ArrayList();
    } else if (tareas.size() > numReg) {
        double divNumPage = (double) tareas.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 5;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar tareas</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <br>
            <br>
            <br>
            <br>
                <h4 class="center-align deep-purple-text text-darken-4"><strong>Tareas</strong></h4>
            <form action="tareas" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    <div class="input-field col l8 s12">
                        <input  id="txtNombre" type="text" name="nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <i class="material-icons right">search</i>Buscar
                        <a href="Tareas?accion=create" class="waves-effect waves-light btn green"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs centered responsive-table striped purple lighten-4">
                            <thead>
                                <tr>
                                    <th>Titulo</th>                                      
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Tarea tarea : tareas) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">
                                    <td><%=tarea.getTitulo()%></td>                                       
                                    <td>
                                        <div style="display:flex">
                                            <a href="Tarea?accion=edit&id=<%=tareas.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                                <i class="material-icons">edit</i>
                                            </a>
                                            <a href="Tarea?accion=details&id=<%=tareas.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                                <i class="material-icons">description</i>
                                            </a>
                                            <a href="Tarea?accion=delete&id=<%=tareas.getId()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                                <i class="material-icons">delete</i>
                                            </a>     
                                        </div>
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />        
    </body>
</html>