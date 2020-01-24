package com.pgsoft.service.dto;

import org.springframework.hateoas.core.Relation;

import java.util.HashMap;
import java.util.Map;

@Relation(collectionRelation = "metadata")
public class MetadataDTO implements IPgSoftDTO {
    private final Map<String, Object> metadata = new HashMap<>();

    public MetadataDTO() {
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MetadataDTO)) return false;
        final MetadataDTO other = (MetadataDTO) o;
        if (!other.canEqual(this)) return false;
        final Object this$metadata = this.getMetadata();
        final Object other$metadata = other.getMetadata();
        return this$metadata == null || this$metadata.equals(other$metadata);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MetadataDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $metadata = this.getMetadata();
        result = result * PRIME + ($metadata == null ? 43 : $metadata.hashCode());
        return result;
    }

    public String toString() {
        return "MetadataDTO(metadata=" + this.getMetadata() + ")";
    }
}
