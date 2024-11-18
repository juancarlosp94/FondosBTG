import Head from 'next/head';
import FundList from '../components/FundList';
import UserCreation from '../components/UserCreation';
import SubscriptionManagement from '../components/SubscriptionManagement';
import styles from '../styles/Home.module.css';

export default function Home() {
  return (
    <>
    <div className={styles.container}>
      <Head>
        <title>BTGFondos</title>
      </Head>

      <header className={styles.header}>
        <h1>BTGFondos</h1>
      </header>

      <main className={styles.main}>
        <UserCreation />
        <FundList />
        <SubscriptionManagement />
      </main>
    </div>
    </>
  );
}
