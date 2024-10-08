package com.automation.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DatabaseConfiguration {

    String Host;
    String DBName;
    String Username;
    String Password;
}
