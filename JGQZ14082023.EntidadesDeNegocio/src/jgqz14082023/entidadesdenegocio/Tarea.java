package jgqz14082023.entidadesdenegocio;

public class Tarea {
    private int id;
    private String Titulo;
    private String Descripcion;
    
    private int top_aux;

    public Tarea() {
    }

    public Tarea(int id, String Titulo, String Descripcion) {
        this.id = id;
        this.Titulo = Titulo;
        this.Descripcion = Descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
}
