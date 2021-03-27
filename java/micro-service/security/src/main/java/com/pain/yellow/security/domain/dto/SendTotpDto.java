package com.pain.yellow.security.domain.dto;

import com.pain.yellow.security.util.MfaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendTotpDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private MfaType mfaType = MfaType.SMS;

    @NotNull
    private String mfaId;
}