<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jgqz14082023.appweb.utils.*"%>
<!--Texto en nav en pantalla de escritorio-->
<nav class="red">
    <div class="nav-wrapper container">
        <a href="Home" class="brand-logo">Gisela - Prueba</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down"> 
            <li><a href="Home">Inicio</a></li>
            <li><a href="Tarea">Tareas</a></li>
        </ul>
    </div>
</nav>
<!--Texto en el nav pantalla movil-->
<ul class="sidenav red" id="mobile-demo">
    <li><a href="Home">Inicio</a></li>
    <li><a href="Tarea">Tareas</a></li>
</ul>