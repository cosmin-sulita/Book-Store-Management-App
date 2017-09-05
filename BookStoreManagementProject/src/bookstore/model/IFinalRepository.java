package bookstore.model;

import java.io.Serializable;
import java.util.List;

public interface IFinalRepository extends Serializable {

	void addRepository(IRepository repository);

	IRepository getRepository(int index);

	List<IRepository> getList();

}
