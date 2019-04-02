package automoveis.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import automoveis.dao.ModeloDao;
import automoveis.entidades.Modelo;



@FacesConverter(value = "convModelo", forClass = Modelo.class)
public class ModeloConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value){
		try{
			if(value == null && !"".equals(value)){
				return null;
			}
			ModeloDao mDao = new ModeloDao();
			
			return mDao.getById(Integer.valueOf(value));
		}catch(NumberFormatException e){
			System.out.println("erro------->"+e.getMessage());
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if(value == null){
			System.out.println("Estou aqui para value = null -> getAsString");
			return null;
		}
		try{
			Modelo m = (Modelo) value;
			return Integer.toString(m.getId());
		}catch(Exception e){  
            return "erro: "+e;  
        }  
	}

}