package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.List;

public class ListaRecords implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<RecordPBSingle> listaPBSingle;

	public List<RecordPBSingle> getListaPBSingle() {
		return listaPBSingle;
	}

	public void setListaPBSingle(List<RecordPBSingle> listaPBSingle) {
		this.listaPBSingle = listaPBSingle;
	}

}
