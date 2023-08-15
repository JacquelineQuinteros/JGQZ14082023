Create database JGQZ14082023
go
use JGQZ14082023
go

-- Creación de tabla Tareas
create table Tareas(
	Id int PRIMARY KEY IDENTITY(1,1) NOT NULL,
	Titulo varchar(50) NOT NULL,
	Descripcion varchar(Max) NOT NULL
)
go