package bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class FinalRepository implements IFinalRepository {

	private static final long serialVersionUID = 1L;

	private List<IRepository> repositoryList;

	public FinalRepository() {
		repositoryList = new ArrayList<IRepository>();
	}

	@Override
	public void addRepository(IRepository repository) {
		repositoryList.add(repository);
	}

	@Override
	public IRepository getRepository(int index) {
		return repositoryList.get(index);
	}

}
