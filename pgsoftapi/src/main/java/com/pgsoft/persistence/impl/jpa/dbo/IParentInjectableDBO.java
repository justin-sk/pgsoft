package com.pgsoft.persistence.impl.jpa.dbo;

public interface IParentInjectableDBO<PARENT_DBO_TYPE extends IPgSoftDBO> extends IPgSoftDBO {
    void injectParent(PARENT_DBO_TYPE linkedParent);

    PARENT_DBO_TYPE extractParent();
}
