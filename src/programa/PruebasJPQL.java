package programa;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import entidades.Piloto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class PruebasJPQL {

	public static void main(String[] args) {
		//Creamos la factory de Entidades:
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("gestorF1");
		//A partir del factory, creamos el EntityManager que es el que vamos a usar para generar las transacciones
		EntityManager manager=emf.createEntityManager();
		String jpql="SELECT p FROM Piloto p where p.puntosClasificacion>=80";
		Query query=manager.createQuery(jpql);
		List<Piloto>pilotos= query.getResultList();
		Iterator<Piloto> iteraPiloto=pilotos.iterator();
		while(iteraPiloto.hasNext()) {
			System.out.println(iteraPiloto.next());
		}
		
		String jpql2="SELECT p.nombre FROM Piloto p";
		query=manager.createQuery(jpql2);
		List<Object> pilotos2=query.getResultList();
		String nombre=null;
		for(Object p:pilotos2) {
			nombre=String.valueOf(p);
			System.out.println(nombre);
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		String jpql3="SELECT p.escuderia,p.nombre,p.numPiloto from Piloto p";
		query=manager.createQuery(jpql3);
		List<Object[]> pilotos3=query.getResultList();
		for(Object[] datos:pilotos3) {
			System.out.println("Número: "+datos[2]+"\nNombre: "+datos[1]+"\nEscuderia: "+datos[0]+"\n---------------------");
		}
		
		//UPDATE
		String jpql4="UPDATE Piloto p SET p.puntosClasificacion=p.puntosClasificacion+25 WHERE p.nombre='Max Verstappen'";
		String jpql5="UPDATE Piloto p SET p.puntosClasificacion=p.puntosClasificacion+18 WHERE p.nombre='Carlos Sainz Jr.'";
		String jpql6="UPDATE Piloto p SET p.puntosClasificacion=p.puntosClasificacion+15 WHERE p.nombre='Fernando Alonso'";
		
		Query q1=manager.createQuery(jpql4);
		Query q2=manager.createQuery(jpql5);
		Query q3=manager.createQuery(jpql6);
		//Como un UPDATE modifica la base de datos, tengo que hacerlo dentro de la transacción
		EntityTransaction transaccion=manager.getTransaction();
		transaccion.begin();
		int val1=q1.executeUpdate();
		int val2=q2.executeUpdate();
		int val3=q3.executeUpdate();
		transaccion.commit();
		if(val1==1&&val2==1&&val3==1) {
			System.out.println("Éxito");
		}	
		
		//DELETE
		String jpql7="DELETE FROM Piloto p WHERE p.escuderia='Alpine'";
		Query q4=manager.createQuery(jpql7);
		transaccion=manager.getTransaction();
		transaccion.begin();
		val1=q4.executeUpdate();
		transaccion.commit();
	
	}
}
