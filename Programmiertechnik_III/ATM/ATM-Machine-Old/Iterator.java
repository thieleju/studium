/**
 * Diese Schnittstelle stellt Methoden zur Verfügung, um durch mehrere Objekte
 * zu iterieren.
 * 
 * @author DanH957
 */
public interface Iterator {

	boolean hasNext(int position);

	Object next(int position);

	boolean hasPrev(int position);

}
