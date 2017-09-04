package bookstore.model;

import java.io.Serializable;

public interface IFinalRepository extends Serializable {

	void addRepository(IRepository repository);

	IRepository getRepository(int index);

}
