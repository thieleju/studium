import java.util.ArrayList;

/**
 * Diese Klasse wird verwendet, um durch die Liste der Accounts zu iterieren.
 * 
 * @author DanH957
 */
public class AccountIterator implements Iterator {
	ArrayList<Account> accounts;

	public AccountIterator(ArrayList<Account> accounts2) {
		this.accounts = accounts2;
	}

	/**
	 * Überprüft, ob noch ein nächstes Element vorhanden ist.
	 * 
	 * @param position Position des aktuellen Elementes
	 * @return true, wenn es ein nächstes Element gibt, sonst false
	 */
	public boolean hasNext(int position) {
		if (position >= accounts.size()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gibt das nächste Element zurück.
	 * 
	 * @param position Position des aktuellen Elementes
	 * @return Das nächste Element
	 */
	@Override
	public Object next(int position) {
		Account AccountItem = accounts.get(position);
		return AccountItem;
	}

	/**
	 * Überprüft, ob noch ein nächstes Element vorhanden ist.
	 * 
	 * @param position Position des aktuellen Elementes
	 * @return true, wenn es ein nächstes Element gibt, sonst false
	 */
	@Override
	public boolean hasPrev(int position) {
		if (position == 0)
			return false;
		else
			return true;
	}

}
