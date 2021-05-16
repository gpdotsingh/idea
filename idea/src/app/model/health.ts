export interface Details {
    total: number;
    free: number;
    threshold: number;
    exists: boolean;
}

export interface DiskSpace {
    status: string;
    details: Details;
}

export interface Ping {
    status: string;
}

export interface Components {
    diskSpace: DiskSpace;
    ping: Ping;
}

export interface Health {
    status: string;
    components: Components;
}
