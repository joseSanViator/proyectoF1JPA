package programa;

import entidades.Piloto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Principal {

	public static void main(String[] args) {
	
		Piloto p1=new Piloto(14,"Fernando Alonso","Aston Martin",33);
		Piloto p2=new Piloto(1,"Max Verstappen","Red Bull",136);
		Piloto p3=new Piloto(4,"Carlos Sainz Jr.","Red Bull",0);
		Piloto p4=new Piloto(31,"Esteban Occon","Alpine",1);
		//Creamos la factory de Entidades:
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("gestorF1");
		//A partir del factory, creamos el EntityManager que es el que vamos a usar para generar las transacciones
		EntityManager manager=emf.createEntityManager();
		
		//Vamos a crear una transacción para realizar una acción en la BD (en este caso, será insertar un objeto Piloto en la BD)
		EntityTransaction transaccion=manager.getTransaction();
		//Inicializo la transacción:
		transaccion.begin();
		//Ahora vamos a realizar todas las acciones que queramos hacer en esta transacción
		//...
		//Quiero persistir (guardar en la BD) un objeto de tipo Piloto.
		//Para ello, usaré el método persist(Object o) de la clase EntityManager:
		//manager.persist(p4);
		
		//...
		//Una vez he ralizado todas las acciones que quiero hacer en la transacción, ejecuto esta transacción
		transaccion.commit();	
				
		//Vamos a intentar recuperar el objeto de la base de datos (el primer parámetro es la clase del objeto que busco y el segundo el ID):
		Piloto pilotoRecuperado=manager.find(Piloto.class, 1);
		if(pilotoRecuperado!=null) {
			System.out.println(pilotoRecuperado);
		}else {
			System.out.println("Piloto con id 33 no encontrado");
		}
		
		
		//Vamos a actualizar los datos de Carlos Sanz Jr. para ello:
		//Primero lo buscamos en la BD por ID
		Piloto pilotoActualizar=manager.find(Piloto.class, 4);
		if(pilotoActualizar!=null) {
			//Creo la transacción:
			EntityTransaction tran=manager.getTransaction();
			tran.begin();
			//Las actualizaciones al objeto que haga entre el begin() y el commit() se reflejarán en la BD
			pilotoActualizar.setEscuderia("Ferrari");
			pilotoActualizar.setPuntosClasificacion(85);
			tran.commit();
		}
		//Vamos a buscar a Esteban Occon por ID y vamos a borrarlo de la BD
		Piloto pilotoBorrar=manager.find(Piloto.class, 31);
		if(pilotoBorrar!=null) {
			EntityTransaction tran=manager.getTransaction();
			tran.begin();
			//Al método remove() le paso como parámetro de entrada el objeto Piloto que queremos borrar , en este caso el de Esteban Occon
			manager.remove(pilotoBorrar);
			tran.commit();
		}
		
	}
}
