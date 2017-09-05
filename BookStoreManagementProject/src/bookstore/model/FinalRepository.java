package bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class FinalRepository implements IFinalRepository {

	private static final long serialVersionUID = 1L;

	private List<IRepository> repositoryList;

	public FinalRepository() {
		repositoryList = new ArrayList<IRepository>();
	}

	public void addRepository(IRepository repository) {
		repositoryList.add(repository);
	}

	public IRepository getRepository(int index) {
		return repositoryList.get(index);
	}

	@Override
	public List<IRepository> getList() {
		return repositoryList;
	}

}
