package bookstore.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import bookstore.builders.ISupplierBuilder;
import bookstore.builders.SupplierBuilder;
import bookstore.model.ISupplier;
import bookstore.model.ISupplierRepository;
import bookstore.view.AddInvoicePanel;
import bookstore.view.AddSupplierFrame;
import bookstore.view.BookStoreInterface;

public class SupplierController implements Serializable {

	private static final long serialVersionUID = 1L;

	public void addNewSupplier(AddSupplierFrame asf, AddInvoicePanel aip, ISupplierRepository supplierRepository) {

		boolean nameAlreadyExists = false;
		boolean AllFieldsAreFilled = false;

		String name;

		ISupplier supplier;
		ISupplierBuilder supplierBuilder;

		if (!asf.getName().contentEquals("")) {
			AllFieldsAreFilled = true;
		}

		if (AllFieldsAreFilled) {
			try {
				name = asf.getName();
				nameAlreadyExists = supplierRepository.doesNameAlreadyExist(name);

				if (nameAlreadyExists) {
					JOptionPane.showMessageDialog(asf, name + " already exists!\nPlease use another name!");
				} else {

					supplierBuilder = new SupplierBuilder();
					supplier = supplierBuilder.build(name);

					supplierRepository.addSupplierToList(supplier);
					sortSupplierListAlphabetically(supplierRepository.getList());
					int index = supplierRepository.getList().indexOf(supplier);

					aip.insertSupplierInComboBox(supplier, index);
					asf.setTextFieldName("");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(asf, "Isbn is not a number!");
			}
		} else {
			JOptionPane.showMessageDialog(asf, "Please fill out all non-optional fields");
		}
	}

	public void sortSupplierListAlphabetically(List<ISupplier> supplierList) {
		Collections.sort(supplierList, new Comparator<ISupplier>() {
			@Override
			public int compare(ISupplier firstSupplier, ISupplier secondSupplier) {
				return firstSupplier.getName().compareTo(secondSupplier.getName());
			}
		});
	}

}
