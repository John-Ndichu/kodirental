CREATE TABLE regions (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_regions_name UNIQUE (name)
);

CREATE TABLE areas (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255) NOT NULL,
    region_id   UUID NOT NULL REFERENCES regions (id),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_areas_region_name UNIQUE (region_id, name)
);

CREATE TABLE contributors (
    id               UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name       VARCHAR(255) NOT NULL,
    last_name        VARCHAR(255) NOT NULL,
    hashed_password  VARCHAR(255) NOT NULL,
    email            VARCHAR(255) NOT NULL,
    enabled          BOOLEAN NOT NULL DEFAULT TRUE,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uq_contributors_email UNIQUE (email)
);

CREATE TABLE rent_submissions (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    contributor_id      UUID NOT NULL REFERENCES contributors (id),
    area_id             UUID NOT NULL REFERENCES areas (id),
    house_type          VARCHAR(32) NOT NULL,
    amount              NUMERIC(38, 2) NOT NULL,
    status              VARCHAR(32) NOT NULL,
    utilities_included  BOOLEAN NOT NULL,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT chk_rent_house_type CHECK (house_type IN (
        'SINGLE_ROOM', 'BEDSITTER', 'STUDIO', 'ONE_BEDROOM',
        'TWO_BEDROOM', 'THREE_BEDROOM', 'BUNGALOW', 'MAISONETTE')),
    CONSTRAINT chk_rent_status CHECK (status IN ('ACTIVE', 'FLAGGED', 'REMOVED'))
);

CREATE INDEX idx_areas_region_id ON areas (region_id);
CREATE INDEX idx_rent_submissions_contributor_id ON rent_submissions (contributor_id);
CREATE INDEX idx_rent_submissions_area_id ON rent_submissions (area_id);
CREATE INDEX idx_rent_submissions_status ON rent_submissions (status);