package ua.com.smiddle.smiddlecontactmanager.model.type.metadata;

import lombok.*;

@Data
@Builder
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
public class Field implements Cloneable {
    // Immutable
    private String name;
    private String title;
    private String importName;
    private boolean searchable;
    private int length;
    private DataType dataType;
    private InputType inputType;
    // TODO: 05-Sep-18 Dictionary as separate entity
    private String dictionary;

    // TODO: 13-Sep-18 Manually clone dictionary
    @Override
    public final Field clone() {
        try {
            return (Field) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning error", e);
        }
    }
}
