/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.modelo.rubik.estadisticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaRecords implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<RecordPBSingle> listaPBSingle;

	public ListaRecords() {
		super();
		this.listaPBSingle = new ArrayList<RecordPBSingle>();
	}

	public List<RecordPBSingle> getListaPBSingle() {
		return listaPBSingle;
	}

	public void setListaPBSingle(List<RecordPBSingle> listaPBSingle) {
		this.listaPBSingle = listaPBSingle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<ListaRecords>");
		if (listaPBSingle != null) {
			for (RecordPBSingle recordPBSingle : listaPBSingle) {
				builder.append(recordPBSingle);
			}
		}
		builder.append("</ListaRecords>");
		return builder.toString();
	}

}
