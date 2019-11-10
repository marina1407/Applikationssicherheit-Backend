package ch.common.business;

import ch.common.controller.BenutzerController;
import ch.common.controller.model.UpdateBenutzerDto;
import ch.common.exception.EmailExistsException;
import ch.common.exception.InvalidCredentialsException;
import ch.common.exception.NoEntityFoundException;
import ch.common.model.dto.BenutzerDto;
import ch.common.model.po.BenutzerPo;
import ch.common.persistence.dao.DataAccessObject;
import ch.common.util.Hasher;
import ch.common.util.PoDtoMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

/**
 * Persistenz-Schicht des Benutzers
 * 
 * @author Marina
 *
 */
@Stateless
public class BenutzerDAO {

	static Logger logger = Logger.getLogger(BenutzerDAO.class);

	@EJB
	private DataAccessObject dataAccessObject;

	/**
	 * Hinzufügen eines Benutzers.
	 * 
	 * @param vorname
	 * @param nachname
	 * @param email
	 * @param passwort
	 * @return
	 * @throws EmailExistsException
	 */
	public BenutzerDto addBenutzer(String vorname, String nachname, String email, String passwort)
			throws EmailExistsException {

		BenutzerPo benutzerWithEmail = dataAccessObject.getBenutzerByEmail(email);
		if (benutzerWithEmail != null) {
			EmailExistsException exception = new EmailExistsException(
					"Es existiert bereits ein Benutzer mit der Email-Adresse " + email);
			logger.error(exception);
			throw exception;
		}

		BenutzerPo newBenutzer = new BenutzerPo();
		newBenutzer.setVorname(vorname);
		newBenutzer.setNachname(nachname);
		newBenutzer.setEmail(email);
		newBenutzer.setPasswort(Hasher.hashString(passwort));

		newBenutzer = dataAccessObject.<BenutzerPo>persist(newBenutzer);
		logger.info("Der Benutzer mit der ID " + newBenutzer.getId() + " konnte erfolgreich erstellt werden. ");

		return PoDtoMapper.benutzerMapper(newBenutzer);
	}

	/**
	 * Benutzername und Passwort überprüfen.
	 * 
	 * @param email
	 * @param passwort
	 * @return
	 * @throws InvalidCredentialsException
	 * @throws NoEntityFoundException
	 */
	public BenutzerDto verifyBenutzer(String email, String passwort)
			throws InvalidCredentialsException, NoEntityFoundException {
		BenutzerPo benutzerWithEmail = dataAccessObject.getBenutzerByEmail(email);
		if (benutzerWithEmail == null) {
			NoEntityFoundException noEntityFoundException = new NoEntityFoundException("Es existiert bereits ein Benutzer mit dieser Email-Adresse");
			logger.error(noEntityFoundException);
			throw noEntityFoundException;
		}

		String hashedPassword = Hasher.hashString(passwort);

		if (!hashedPassword.equals(benutzerWithEmail.getPasswort())) {
			InvalidCredentialsException invalidCredentialsException = new InvalidCredentialsException("Passwörter stimmen nicht überein");
			logger.error(invalidCredentialsException);
			throw invalidCredentialsException;
		}
		return PoDtoMapper.benutzerMapper(benutzerWithEmail);
	}

	/**
	 * Benutzer mit ID holen.
	 * 
	 * @param id
	 * @return
	 * @throws NoEntityFoundException
	 */
	public BenutzerDto getBenutzer(int id) throws NoEntityFoundException {

		return PoDtoMapper.benutzerMapper(getBenutzerEntity(id));
	}

	public BenutzerPo getBenutzerEntity(int id) throws NoEntityFoundException {

		BenutzerPo benutzer = dataAccessObject.get(id, BenutzerPo.class);
		if (benutzer == null) {
			throw new NoEntityFoundException("Es existiert kein Benutzer mit dieser Id");
		}
		return benutzer;
	}

	public BenutzerDto updateBenutzer(UpdateBenutzerDto benutzer) throws NoEntityFoundException {
		BenutzerPo persistedBenutzer = dataAccessObject.get(benutzer.getId(), BenutzerPo.class);
		if (persistedBenutzer == null) {
			throw new NoEntityFoundException("Es existiert kein Benutzer mit dieser Id");
		}

		if (benutzer.getVorname() != null) {
			persistedBenutzer.setVorname(benutzer.getVorname());
		}
		if (benutzer.getNachname() != null) {
			persistedBenutzer.setNachname(benutzer.getNachname());
		}
		if (benutzer.getEmail() != null) {
			persistedBenutzer.setEmail(benutzer.getEmail());
		}
		if (benutzer.getPasswort() != null) {
			persistedBenutzer.setPasswort(Hasher.hashString(benutzer.getPasswort()));
		}

		dataAccessObject.persist(persistedBenutzer);
		return PoDtoMapper.benutzerMapper(persistedBenutzer);
	}

	public void removeBenutzer(int id) throws NoEntityFoundException {
		BenutzerPo benutzer = dataAccessObject.get(id, BenutzerPo.class);
		if (benutzer == null) {
			throw new NoEntityFoundException("Es existier kein Benutzer mit dieser Id.");
		}
		dataAccessObject.remove(benutzer);
	}

}
