package services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.Collection;
import domain.CreditCard;
import repositories.CreditCardRepository;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository		creditCardRepository;

	//Supporting Services -------------------


	//CRUD Methods -------------------------

	public CreditCard create() {
		CreditCard res = new CreditCard();
		
		return res;
	}
	
	public CreditCard findOne(int creditCardId) {
		Assert.isTrue(creditCardId != 0);
		CreditCard res = creditCardRepository.findOne(creditCardId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}
	
	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		
		
		return creditCardRepository.save(creditCard);
	}
	
	public void delete(final int creditCardId) {
		Assert.isTrue(creditCardId != 0);
		creditCardRepository.delete(creditCardId);
	}
	
	
}