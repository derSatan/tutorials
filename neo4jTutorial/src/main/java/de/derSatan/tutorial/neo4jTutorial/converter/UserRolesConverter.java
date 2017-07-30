package de.derSatan.tutorial.neo4jTutorial.converter;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import de.derSatan.tutorial.neo4jTutorial.model.User;

public class UserRolesConverter implements AttributeConverter<User.SecurityRole[],String[]> {

    @Override
    public String[] toGraphProperty(User.SecurityRole[] value) {
        if(value==null) {
            return null;
        }
        String[] values = new String[(value.length)];
        int i=0;
        for(User.SecurityRole role : value) {
            values[i++]=role.name();
        }
        return values;
    }

    @Override
    public User.SecurityRole[] toEntityAttribute(String[] value) {
        User.SecurityRole[] roles =new User.SecurityRole[value.length];
        int i=0;
        for(String role : value) {
            roles[i++] = User.SecurityRole.valueOf(role);
        }
        return roles;
    }
}
