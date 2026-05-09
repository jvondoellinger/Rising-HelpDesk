import { UUID } from 'node:crypto';
import * as punycode from 'node:punycode';

export class Ticket {
  constructor(
    public id: UUID,
    public protocol: String,
    public title: string,
    public queue: Queue,
    public deadline: Date,
    public mentions: String[],
    public state: String,
    public openedOn: Date,
    public lastUpdatedOn: Date,
  ) {}

  public getMentionsText(): String {
    return this.mentions.join(',');
  }

  public getDurationText(): String {
    const diffMs = Date.now() - this.openedOn.getTime();

    const days = Math.ceil(diffMs / (1000 * 60 * 60 * 24));

    return `${days} ${days === 1 ? 'dia' : 'dias'}`;
  }
  public getOpenedOnText(): String {
    return this.formateDate(this.openedOn);
  }
  public getLastUpdatedOnText(): String {
    return this.formateDate(this.lastUpdatedOn);
  }

  protected formateDate(date: Date): String {
    const currentYear = new Date().getFullYear();
    const year = date.getFullYear();

    return new Intl.DateTimeFormat('pt-BR', {
      day: '2-digit',
      month: '2-digit',
      ...(year !== currentYear && { year: 'numeric' }),
    }).format(date);
  }
}

export class Queue {
  constructor(
    public id: UUID,
    public area: string,
    public subarea: string,
  ) {
  }
}
