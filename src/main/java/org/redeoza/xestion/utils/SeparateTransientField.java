package org.redeoza.xestion.utils;

import org.apache.commons.lang3.StringUtils;
import org.redeoza.xestion.model.Socio;
import org.springframework.stereotype.Component;

@Component
public class SeparateTransientField {

    public void separateSocNomComp(Socio socio) {
        socio.setSocApe2(socio.getSocNomComp().substring(0, socio.getSocNomComp().indexOf(StringUtils.SPACE)).trim());
        socio.setSocApe1(socio.getSocNomComp().substring(socio.getSocNomComp().indexOf(StringUtils.SPACE), socio.getSocNomComp().indexOf(',')).trim());
        socio.setSocNom(socio.getSocNomComp().substring(socio.getSocNomComp().indexOf(", ")).replace(", ", ""));
    }
}
