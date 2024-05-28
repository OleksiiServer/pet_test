package epam.data;

import epam.api.dto.Pet;

public class TestData {
    public static Pet getTestPet() {
        return new Pet(1, null, "doggie", null, null, "available");
    }
}
