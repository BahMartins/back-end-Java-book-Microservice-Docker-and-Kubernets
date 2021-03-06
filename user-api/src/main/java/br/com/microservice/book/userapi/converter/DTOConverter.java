package br.com.microservice.book.userapi.converter;

import br.com.microservice.book.userapi.dto.UserDTO;
import br.com.microservice.book.userapi.model.User;

public class DTOConverter {
	
	public static UserDTO convert(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setName(user.getName());
		userDto.setAddress(user.getAddress());
		userDto.setCpf(user.getCpf());
		return userDto;
	}

}
