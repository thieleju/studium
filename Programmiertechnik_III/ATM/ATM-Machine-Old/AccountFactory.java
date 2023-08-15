
/**
 * Klasse, die einen Account erzeugt
 * 
 * @author DanH957
 */
public class AccountFactory extends Account {

	public AccountFactory(String Username, int theAccountNumber, int thePIN, double theAvailableBalance,
			double theTotalBalance, int isadmin) {
		super(Username, theAccountNumber, thePIN, theAvailableBalance, theTotalBalance, isadmin);
		setUsername(Username);
		setAccountNumber(theAccountNumber);
		setPin(thePIN);
		setAvailableBalance(theAvailableBalance);
		setTotalBalance(theTotalBalance);
		setAdmin(isadmin);
	}
}
