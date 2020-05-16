package org.redeoza.xestion.utils;

import org.apache.commons.lang3.StringUtils;
import org.redeoza.xestion.model.Socio;
import org.springframework.stereotype.Component;

@Component
public class ManipulationTransientField {

    public static void concatenateSocNomComp(Socio socio) {
        socio.setSocNomComp(socio.getSocApe2()
                .concat(StringUtils.SPACE)
                .concat(socio.getSocApe1())
                .concat(UtilConstant.COMA)
                .concat(socio.getSocNom()));
    }

    public void separateSocNomComp(Socio socio) {
        socio.setSocApe2(socio.getSocNomComp().substring(0, socio.getSocNomComp().indexOf(StringUtils.SPACE)).trim());
        socio.setSocApe1(socio.getSocNomComp()
                .substring(socio.getSocNomComp().indexOf(StringUtils.SPACE),
                        socio.getSocNomComp().indexOf(UtilConstant.COMA)).trim());
        socio.setSocNom(socio.getSocNomComp()
                .substring(socio.getSocNomComp()
                        .indexOf(", ")).replace(", ", ""));
    }
}
